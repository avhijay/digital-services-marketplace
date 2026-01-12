package com.marketplace.order_service.dto.internal;



import java.util.List;

public record ValidateProductsResponse(

        List<ValidateProductResponse> validateProductResponses

)

{

}