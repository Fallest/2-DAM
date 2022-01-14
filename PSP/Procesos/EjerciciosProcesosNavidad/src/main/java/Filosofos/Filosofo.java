class Filosofo implements Runnable {

    private String idFilosofo;
    private int tenedorInstruccion;
    Object tenedorDerecho;
    Object tenedorIzquierdo;

    public Filosofo(String idFilosofo, Object tenedorDerecho, Object tenedorIzquierdo, int tenedorInstruccion) {
        this.idFilosofo = idFilosofo;
        this.tenedorDerecho = tenedorDerecho;
        this.tenedorIzquierdo = tenedorIzquierdo;
        this.tenedorInstruccion = tenedorInstruccion;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println(idFilosofo + " con ganas de comer");
                if (this.tenedorInstruccion == 1) {
                    synchronized (tenedorDerecho) {
                        tomarTenedor("derecho");
                        synchronized (tenedorIzquierdo) {
                            tomarTenedor("izquierdo");
                            comer();
                            this.tenedorInstruccion = 0;
                        }
                    }
                    pensar();
                } if (this.tenedorInstruccion == 2) {
                    synchronized (tenedorIzquierdo){
                        tomarTenedor("izquierdo");
                        synchronized (tenedorDerecho) {
                            tomarTenedor("derecho");
                            comer();
                            this.tenedorInstruccion = 0;
                        }
                    }
                    pensar();
                }
                synchronized (tenedorDerecho) {
                    tomarTenedor("derecho");
                    synchronized (tenedorIzquierdo) {
                        tomarTenedor("izquierdo");
                        comer();
                    }
                }
                synchronized (tenedorIzquierdo){
                    tomarTenedor("izquierdo");
                    synchronized (tenedorDerecho) {
                        tomarTenedor("derecho");
                        comer();
                    }
                }
                pensar();
        }
    }

    public void pensar() {
        try {
            System.out.println(idFilosofo + " está pensando");
            int pausa = (int) (Math.random() * 3000);
            Thread.sleep(pausa);
        } catch (InterruptedException e) { //Hay que capturar la excepcion
            
            System.out.println(e.toString());
        }
    }

    public void tomarTenedor(String tenedor) {
        try {
            System.out.println(idFilosofo + " toma su tenedor " + tenedor);
            int pausa = (int) (Math.random() * 3000);
            Thread.sleep(pausa);
        } catch (InterruptedException e) {   //Hay que capturar la excepcion
            System.out.println(e.toString());
        }
    }

    public void comer() {
        try{    
            System.out.println(idFilosofo + " está comiendo");
            int pausa = (int) (Math.random() * 3000);
            Thread.sleep(pausa);
            System.out.println(idFilosofo + " deja de comer");
        } catch (InterruptedException e) {  //Hay que capturar la excepcion
            System.out.println(e.toString());
        }
    }
}