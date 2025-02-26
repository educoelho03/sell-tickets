package br.tech.tickets.controller.dto;

public record LoginResponse(String accessToken, Long expiresIn) {
}
