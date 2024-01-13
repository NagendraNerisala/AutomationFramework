package api.payload;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.annotations.SerializedName;
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Data {
    /*Variables of Data class*/
    @SerializedName("year")
    private int year;

    @SerializedName("price")
    private double price;

    @SerializedName("CPU model")
    private String CPU_model;

    @SerializedName("Hard disk size")
    private String Hard_disk_size;


    /*Getter and Setter methods*/
    public void setYear(int year)
    {
        this.year = year;
    }
    public int getYear()
    {
        return year;
    }
    public void setPrice(double price)
    {
        this.price = price;
    }
    public double getPrice()
    {
        return price;
    }

    public void setCPU_model(String CPU_model)
    {
        this.CPU_model = CPU_model;
    }
    public String getCPU_model()
    {
        return CPU_model;
    }

    public void setHard_disk_size(String Hard_disk_size)
    {
        this.Hard_disk_size = Hard_disk_size;
    }
    public String getHard_disk_size()
    {
        return Hard_disk_size;
    }

}

