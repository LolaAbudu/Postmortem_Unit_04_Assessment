package org.pursuit.postmortemunit04assessment.network;

import org.pursuit.postmortemunit04assessment.model.EchinodermAnimals;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AnimalService {

    String ECHINODERMS_ENDPOINT = "JDVila/storybook/master/echinoderms.json";

   //What is an endpoint
   //Know difference between GET/POST/??
   //What type is this interface returning?
    @GET(ECHINODERMS_ENDPOINT)
    Call<EchinodermAnimals> getAnimals();
}
