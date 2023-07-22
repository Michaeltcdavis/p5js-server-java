public class Main {

    public static void main(String[] args) {
        SimpleServer server = new SimpleServerBuilder().setPort(9991).build();

        try {
        server.start();
        } catch (Exception e) {
            System.err.println("server failed to start");
            e.printStackTrace();
        }
    }
}
