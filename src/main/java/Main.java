import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        GoogleWebService service = new Retrofit.Builder()
                .baseUrl("https://google.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build()
                .create(GoogleWebService.class);

        Response<String> response = null;
        try {
            response = service.index().execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (response != null && response.isSuccessful()) {
            System.out.println(response.body());
        } else if (response != null) {
            System.out.println(response.errorBody().string());
            try {
                throw new IOException();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        GitHubService service1 = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build()
                .create(GitHubService.class);

        Response<String> responseHit = null;
        try {
            responseHit = service1.listRepos("BIONIX77-JAVA").execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (responseHit != null && responseHit.isSuccessful()) {
            System.out.println(responseHit.body());
        } else if (responseHit != null) {
            System.out.println(responseHit.errorBody().string());
            try {
                throw new IOException();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        final TinyUrlService serviceUrl = new Retrofit.Builder()
                .baseUrl("http://tiny-url.info/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TinyUrlService.class);

        Response<TinyUrlResponse> responseUrl;
        responseUrl = serviceUrl.createShortLink("json", "http://tiny-url.info/open_api.html").execute();

        if (responseUrl != null && responseUrl.isSuccessful()) {
            System.out.println(responseUrl.body().shortUrl);
        } else if (responseUrl != null) {
            System.out.println(responseUrl.errorBody().string());
        }
    }
}