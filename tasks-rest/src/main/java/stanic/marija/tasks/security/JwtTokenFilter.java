package stanic.marija.tasks.security;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import org.springframework.web.filter.OncePerRequestFilter;

import stanic.marija.tasks.exception.ApiErrorException;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

  private JwtTokenProvider jwtTokenProvider;

  public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
    this.jwtTokenProvider = jwtTokenProvider;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
	  String bearerToken = httpServletRequest.getHeader("Authorization");
	  Optional<String> token = jwtTokenProvider.resolveToken(bearerToken);
    try {
      if (token.isPresent() && jwtTokenProvider.validateToken(token.get())) {
        Authentication auth = jwtTokenProvider.getAuthentication(token.get());
        SecurityContextHolder.getContext().setAuthentication(auth);
      }
    } catch (ApiErrorException ex) {
      SecurityContextHolder.clearContext();
      httpServletResponse.sendError(ex.getHttpStatus().value(), ex.getMessage());
      return;
    }

    filterChain.doFilter(httpServletRequest, httpServletResponse);
  }

}
