public class SimpleServerBuilder {

    private int _PORT = 9991;

    public SimpleServerBuilder() {
    }

    public SimpleServerBuilder setPort(int port) {
        _PORT = port;
        return this;
    }

    public SimpleServer build() {
        return new SimpleServer(_PORT);
    }
}
