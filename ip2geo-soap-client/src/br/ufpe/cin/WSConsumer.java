package br.ufpe.cin;

public class WSConsumer {
    public static void main(String[] args) {
        IP2GeoHttpGet ip2GeoHttpGet = new IP2Geo().getIP2GeoHttpGet();
        IPInformation information = ip2GeoHttpGet.resolveIP("177.23.168.9", "0");
        System.out.println("information.getCity() = " + information.getCity());
    }
}
