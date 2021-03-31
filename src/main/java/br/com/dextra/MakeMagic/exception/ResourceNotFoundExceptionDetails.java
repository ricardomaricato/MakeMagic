package br.com.dextra.MakeMagic.exception;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@Data
@SuperBuilder
public class ResourceNotFoundExceptionDetails extends ExceptionDetails {

}
