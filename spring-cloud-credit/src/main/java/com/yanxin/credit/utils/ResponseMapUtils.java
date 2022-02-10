package com.yanxin.credit.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;


@Slf4j
public class ResponseMapUtils {

    public static void responseMapMessage(HttpServletRequest request, HttpServletResponse response, int code, String message) {

        Map<String, Object> map = new TreeMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        map.put("code", code);
        map.put("message", message);
        map.put("path", request.getServletPath());
        map.put("timestamp", String.valueOf(System.currentTimeMillis()));
        String str = null;
        try {
            str = objectMapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            log.error("JsonProcessingException:{}", e.getMessage());
        }
        try {
            response.getWriter().write(str);
        } catch (IOException e) {
            log.error("IO writeException:{}", e.getMessage());

        }

    }
}
