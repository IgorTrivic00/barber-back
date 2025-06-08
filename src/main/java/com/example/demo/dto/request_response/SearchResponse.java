package com.example.demo.dto.request_response;

import java.util.List;

public record SearchResponse<T>(List<T> data,
                                long total) {
}
