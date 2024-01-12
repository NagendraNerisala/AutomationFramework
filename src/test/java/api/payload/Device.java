package api.payload;

import com.google.gson.annotations.SerializedName;

public class Device {

    @SerializedName("id")
    String id;

    @SerializedName("name")
    String name;

    @SerializedName("data")
    Data data;


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

