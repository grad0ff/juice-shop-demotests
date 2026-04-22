package me.vodarga.api.dto;

import me.vodarga.core.model.User;

public record AuthData(String url, String apiPath, User user) {

}
