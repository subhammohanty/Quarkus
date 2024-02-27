package org.hprtech.mapper;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.hprtech.dto.ErrorMessage;
import org.hprtech.exception.BusinessException;

@Provider
public class BusinessExceptionMapper implements ExceptionMapper<BusinessException> {
    @Override
    public Response toResponse(BusinessException e) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setStatus(e.getStatus());
        errorMessage.setMessage(e.getMessage());
        return Response.
//                status(Response.Status.INTERNAL_SERVER_ERROR)
        status(e.getStatus())
                .entity(errorMessage)
                .type(MediaType.APPLICATION_JSON).build();
    }
}
