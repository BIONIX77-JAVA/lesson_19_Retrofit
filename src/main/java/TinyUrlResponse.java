import com.google.gson.annotations.SerializedName;

class TinyUrlResponse {
    public String string;

    @SerializedName("longurl")
    public String longUrl;

    @SerializedName("shorturl")
    public String shortUrl;

}
