package edu.bbte.idde.vbim2101.spring.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class NotFoundException extends RuntimeException {

}
