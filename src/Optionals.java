import java.util.Optional;

/**
 * Example how to use {@link Optional} to prevent {@link NullPointerException} errors
 * @author Pavel Janecka
 */
public class Optionals {

    public Optionals() {
        Optional<Computer> c1 = Optional.of(new Computer(new Motherboard(new USB("3.0"))));

        // NOT NICE
//        String version = "UNKNOWN";
//        if (c1 != null) {
//            Motherboard mb = c1.getMotherboard();
//            if (mb != null) {
//                USB usb = mb.getUsb();
//                if (usb != null) {
//                    version = usb.getVersion();
//                }
//            }
//        }

        // OPTIONALS
        String usbVersion = c1.flatMap(Computer::getMotherboard).flatMap(Motherboard::getUsb).flatMap(USB::getVersion).orElse("UNKNOWN");
        System.out.println("USB version: " + usbVersion);
    }

    private class Computer {
        private Optional<Motherboard> motherboard;

        private Computer(Motherboard motherboard) {
            this.motherboard = Optional.ofNullable(motherboard);
        }

        public Optional<Motherboard> getMotherboard() {
            return motherboard;
        }
    }

    private class Motherboard {
        private Optional<USB> usb;

        private Motherboard(USB usb) {
            this.usb = Optional.ofNullable(usb);
        }

        public Optional<USB> getUsb() {
            return usb;
        }
    }

    private class USB {
        private Optional<String> version;

        private USB(String version) {
            this.version = Optional.ofNullable(version);
        }

        public Optional<String> getVersion() {
            return version;
        }
    }

    public static void main(String[] args) {
        new Optionals();
    }
}
