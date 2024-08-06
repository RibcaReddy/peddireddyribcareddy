class Computer {
    private String CPU;
    private String RAM;
    private String storage;
    private String GPU;
    private String coolingSystem;
    private Computer(Builder builder) {
        this.CPU = builder.CPU;
        this.RAM = builder.RAM;
        this.storage = builder.storage;
        this.GPU = builder.GPU;
        this.coolingSystem = builder.coolingSystem;
    }
    @Override
    public String toString() {
        return "Computer{" +
                "CPU='" + CPU + '\'' +
                ", RAM='" + RAM + '\'' +
                ", storage='" + storage + '\'' +
                ", GPU='" + GPU + '\'' +
                ", coolingSystem='" + coolingSystem + '\'' +
                '}';
    }
    public static class Builder {
        private String CPU;
        private String RAM;
        private String storage;
        private String GPU;
        private String coolingSystem;
        public Builder(String CPU, String RAM) {
            this.CPU = CPU;
            this.RAM = RAM;
        }
        public Builder setStorage(String storage) {
            this.storage = storage;
            return this;
        }
        public Builder setGPU(String GPU) {
            this.GPU = GPU;
            return this;
        }
        public Builder setCoolingSystem(String coolingSystem) {
            this.coolingSystem = coolingSystem;
            return this;
        }
        public Computer build() {
            return new Computer(this);
        }
    }
}
public class BuilderPatternExample {
    public static void main(String[] args) {
        Computer gamingComputer = new Computer.Builder("Intel i9", "32GB")
                .setStorage("1TB SSD")
                .setGPU("NVIDIA RTX 3080")
                .setCoolingSystem("Liquid Cooling")
                .build();
        Computer officeComputer = new Computer.Builder("Intel i5", "16GB")
                .setStorage("512GB SSD")
                .build();
        System.out.println("Gaming Computer: " + gamingComputer);
        System.out.println("Office Computer: " + officeComputer);
    }
}
