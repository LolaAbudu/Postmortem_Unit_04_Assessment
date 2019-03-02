package org.pursuit.postmortemunit04assessment.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitSingleton {

    //Base url will never change once its set to static & final
    private static final String BASE_URL = "https://raw.githubusercontent.com/";

    private static Retrofit myOneInstance;

    //getInstance() is common naming convention for getting a singleton; it return the only one instance of that call
    //Use public static "constructor" so we don't want to create new instances of this class.
    public static Retrofit getInstance() {
        if (myOneInstance != null) {
            return myOneInstance;
        }
        myOneInstance = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        return myOneInstance;
    }

    private RetrofitSingleton(){}
}
