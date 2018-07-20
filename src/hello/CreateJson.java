package hello;

import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class CreateJson {

    public static void main(String[]args) {
        try {
            URL url1 = new URL("http://192.168.0.4:3000/api/PrivateOwner/");
            URLConnection connection1 = url1.openConnection();
            HttpURLConnection httpconnection1 = (HttpURLConnection)connection1;
            httpconnection1.setRequestMethod("POST");
            httpconnection1.setDoOutput(true);
            httpconnection1.setRequestProperty("Content-type", "application/json");
            String input = "{\"$class\":\"org.acme.vehicle.lifecycle.PrivateOwner\",\"email\":\"AjoeSmith@bangaichi\",\"firstName\":\"Joe\"}";
            System.out.println(input);
            OutputStream outputStream = httpconnection1.getOutputStream();
            outputStream.write(input.getBytes());
            BufferedReader responsebuffer = new BufferedReader(new InputStreamReader(httpconnection1.getInputStream()));
                String output;
                System.out.println("Output from HyperLedger Composer REST Server: ");
                while ((output = responsebuffer.readLine()) != null) {
                  System.out.println(output+"a\n");}
            httpconnection1.disconnect();
        } catch (IOException var5) {
            var5.printStackTrace();
        }
    }
}