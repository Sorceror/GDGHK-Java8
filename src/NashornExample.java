import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * Simple example how to invoke Nashorn JavaScript engine from Java 8
 * @author Pavel Janecka
 */
public class NashornExample {

    public NashornExample() throws ScriptException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        engine.eval("print('Hello World!');");
    }

    public static void main(String[] args) {
        try {
            new NashornExample();
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }
}
