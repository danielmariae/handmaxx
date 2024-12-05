// package br.org.handmaxx.app.error.global;

// import jakarta.ws.rs.core.Response;
// import jakarta.ws.rs.ext.ExceptionMapper;
// import jakarta.ws.rs.ext.Provider;
// import lombok.extern.slf4j.Slf4j;

// @Slf4j  // Lombok para facilitar o uso de logs
// @Provider
// public class GlobalExceptionMapper implements ExceptionMapper<Throwable> {

//     @Override
//     public Response toResponse(Throwable exception) {
//         // Cria a resposta de erro
//         ErrorResponse errorResponse = new ErrorResponse(
//                 exception.getMessage(),
//                 "Ocorreu um erro inesperado: " + exception.getMessage(),
//                 Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()
//         );

//         return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
//                 .entity(errorResponse)
//                 .build();
//     }
// }