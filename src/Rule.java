import com.sun.javafx.binding.StringFormatter;
import sun.net.util.IPAddressUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * Created by stas.o on 22/02/2017.
 */
public class Rule {
    enum Action {ACCEPT, DENY}

    ArrayList<Integer> src = new ArrayList<>();
    ArrayList<Integer> dst = new ArrayList<>();
    ArrayList<Service> svc = new ArrayList<>();
    Action action;

    public static Rule createFromCsvLine(String csvLine){
        String[] splittedRule = csvLine.split(",");
        String[] srcs = splittedRule[0].split(";");
        String[] dsts = splittedRule[1].split(";");
        String[] svcs = splittedRule[2].split(";");
        Rule r = new Rule();
        Arrays.stream(srcs).forEach(ip -> r.src.add(ipv4StringToInt(ip)));
        Arrays.stream(dsts).forEach(ip -> r.dst.add(ipv4StringToInt(ip)));
        Arrays.stream(svcs).forEach(svc -> r.svc.add(Service.createFromString(svc)));
        return r;
    }

    public String getAsCsvLine(){
        StringJoiner sj = new StringJoiner(",");
        sj.add(src.stream().map(ip -> ipv4IntToString(ip)).collect(Collectors.joining(";")));
        sj.add(dst.stream().map(ip -> ipv4IntToString(ip)).collect(Collectors.joining(";")));
        sj.add(svc.stream().map(port -> port.toString()).collect(Collectors.joining(";")));
        return sj.toString();
    }

    private static int ipv4StringToInt(String ipv4str){
        String [] octets = ipv4str.split(".");
        int ip = Integer.parseInt(octets[0]);
        ip = ip << 8;
        ip += Integer.parseInt(octets[1]);
        ip = ip << 8;
        ip += Integer.parseInt(octets[2]);
        ip = ip << 8;
        ip += Integer.parseInt(octets[3]);

        return ip;
    }

    private static String ipv4IntToString(Integer ip){
        return StringFormatter.format("{}.{}.{}.{}", ip >> 24 & 0xff, ip >> 16 & 0xff, ip >> 8 & 0xff, ip & 0xff).toString();
    }
}
