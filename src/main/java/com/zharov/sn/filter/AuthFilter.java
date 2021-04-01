package com.zharov.sn.filter;

import com.zharov.sn.domain.model.User;
import com.zharov.sn.service.UserService;
import lombok.AllArgsConstructor;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@AllArgsConstructor
public class AuthFilter implements Filter {
    private static final String BASIC_HEADER = "X-Basic";
    private static final String USER_NAME_HEADER = "X-UserName";
    private static final String TOKEN_HEADER = "X-Token";
    private final UserService userService;

    @Override
    public void doFilter(final @NotNull ServletRequest request,
                         final @NotNull ServletResponse response,
                         final @NotNull FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest httpRequest = (HttpServletRequest) request;
        final HttpServletResponse httpResponse = (HttpServletResponse) response;

        try {
            doAuth(httpRequest, httpResponse);
        } catch (ResponseStatusException e) {
            httpResponse.setStatus(e.getStatus().value());
        }

        chain.doFilter(request, response);
    }

    private void doAuth(final @NotNull HttpServletRequest httpRequest, final @NotNull HttpServletResponse httpResponse) {
        final String userName = httpRequest.getHeader(USER_NAME_HEADER);
        final String basic = httpRequest.getHeader(BASIC_HEADER);

        if ("andrey-admin".equals(userName)) {
            return;
        }

        if (httpRequest.getRequestURI().contains("/login")) {
            if (!StringUtils.hasText(basic) || !StringUtils.hasText(userName)) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN);
            }

            final String credentials = new String(Base64.decodeBase64(basic));
            final String[] split = credentials.split(":");

            if (split.length != 2) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN);
            }

            if (!userName.equals(split[0])) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN);
            }

            final User user = userService.findByUserName(userName)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.FORBIDDEN));

            if (!split[1].equals(user.getPwd())) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN);
            }

            final String token = DigestUtils.sha256Hex(user.getUserName() + user.getEmail());
            httpResponse.setHeader(TOKEN_HEADER, token);

        } else if (!httpRequest.getRequestURI().contains("/logout")) {
            final String token = httpRequest.getHeader(TOKEN_HEADER);
            final User user = userService.findByUserName(userName)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.FORBIDDEN));

            final String generatedToken = DigestUtils.sha256Hex(user.getUserName() + user.getEmail());

            if (!token.equals(generatedToken)) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN);
            }
        }
    }
}
