/**
 * Created by stas.o on 22/02/2017.
 */
public class Service {
    enum Protocol{TCP, UDP}

    public static Service createFromString(String svcService){
        String [] parts = svcService.split(" ");
        return new Service(Protocol.valueOf(parts[0]), Short.parseShort(parts[1]));
    }

    @Override
    public String toString(){
        return protocol.toString()+" "+port;
    }

    public Service(Protocol protocol, short port) {
        this.protocol = protocol;
        this.port = port;
    }

    public Protocol getProtocol() {

        return protocol;
    }

    public void setProtocol(Protocol protocol) {
        this.protocol = protocol;
    }

    public short getPort() {
        return port;
    }

    public void setPort(short port) {
        this.port = port;
    }

    Protocol protocol;
    short port;
}
