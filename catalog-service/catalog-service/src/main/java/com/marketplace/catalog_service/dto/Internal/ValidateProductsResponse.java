package com.marketplace.catalog_service.dto.Internal;

import java.util.List;

public record ValidateProductsResponse(

        List<ValidateProductResponse> validateProductResponses

)

{

}