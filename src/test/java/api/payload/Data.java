package api.payload;

import com.google.gson.annotations.SerializedName;

public class Data {
    @SerializedName("year")
    int year;

    @SerializedName("price")
    double price;

    @SerializedName("CPU model")
    String CPU_model;

    @SerializedName("Hard disk size")
    String Hard_disk_size;

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

