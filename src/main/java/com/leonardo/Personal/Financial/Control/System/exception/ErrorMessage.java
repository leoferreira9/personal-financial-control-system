package com.leonardo.Personal.Financial.Control.System.exception;

import java.time.LocalDateTime;

public record ErrorMessage(String message, int status, LocalDateTime timestamp, String path) { }
