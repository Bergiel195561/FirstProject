import ratpack.server.RatpackServer;

/**
 * Created by apple on 05/04/17.
 */
public class Main {
    public static void main(String[] args) throws Exception{

        RatpackServer.start(ratpackServerSpec -> ratpackServerSpec.handlers(
                chain -> chain.prefix("fibo",
                        fibb -> fibb.all(ctx -> ctx.render("Jest spoko !"))
        )));
    }
}
