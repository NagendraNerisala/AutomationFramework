package api.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.annotations.SerializedName;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Device {

    /* Variables of Device class */
    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("data")
    private Data data;

    /*Getter and Setter methods*/
    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public void setData(Data data)
    {
        this.data = data;
    }

    public Data getData()
    {
        return data;
    }


}

