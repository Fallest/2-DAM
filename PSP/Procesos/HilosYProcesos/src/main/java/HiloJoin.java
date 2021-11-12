public class HiloJoin extends Thread {
    
    private int _instances;
    
    public HiloJoin(String name, int n) {
        super(name);
        _instances = n;
    }
    
    @Override
    public void run() {
        for(int n = 0; n < _instances; n++){
            System.out.println(this.getName() + " > " +n);
            try {
                sleep(1000);
            } catch (InterruptedException ex) {}
        }
        System.out.println(this.getName() + " terminado.");
    }
}
