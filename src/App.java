public class App {
    public static void limpiarPantalla() {
    try {
        String sistemaOperativo = System.getProperty("os.name");

        if (sistemaOperativo.contains("Windows")) {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } else {
            new ProcessBuilder("clear").inheritIO().start().waitFor();
        }

    } catch (Exception e) {
        // Fallback: imprimir líneas en blanco
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
    }
    public static void main(String[] args) throws Exception {
        int anchura = Integer.parseInt(System.console().readLine("Introduzca la anchura del rectángulo: "));
        int altura = Integer.parseInt(System.console().readLine("Introduzca la altura del rectángulo: "));
        boolean salir = true;
        int x = anchura/2;
        int y = altura/2;
        int vy = 0;
        int vx = 0;
        boolean auto = false;

        do{
            salir = false;
            for(int i=1; i<=altura;i++){
                for(int j=1;j<=anchura;j++){
                    if(i==1 || j==1 || i==altura || j==anchura)
                        System.out.print("*");
                    else if (i==y && j==x)
                        System.out.print("O");
                    else
                        System.out.print(" ");
                }
                System.out.println();
            }
                System.out.println();
            System.out.println("W = arriba");
            System.out.println("A = izquierda");
            System.out.println("S = abajo");
            System.out.println("D = derecha");
            System.out.println("Q = automático");
            System.out.println("X = salir");
            String opcion = System.console().readLine().toUpperCase();
            if(!auto){
                switch (opcion) {
                case "W":
                        y = Math.max(2 , y - 1);
                        break;
                    case "A":
                        x = Math.max(2, x - 1);
                        break;
                    case "S":
                        y = Math.min(altura - 1 , y + 1);
                        break;
                    case "D":
                        x = Math.min(anchura - 1, x + 1);
                        break;
                    case "Q":
                        do{
                            vx = ((int)(Math.random()*2+1))*((int)(Math.random()*3-1));
                            vy = ((int)(Math.random()*2+1))*((int)(Math.random()*3-1));
                        }while(vx == 0 || vy == 0);
                        auto = true;
                        break;
                    case "X":
                        salir = true;
                        break;
                    default:
                        System.out.println("Seleccione una opción valida.");
                        break;
                }
            }
            else{
                x+=vx;
                y+=vy;
                if(x <= 1 && vx < 0 || vx >= anchura && vx > 0)
                    x=vx<0?2:anchura-1;
                    vx*= -1;
                if(y <= 1 && vy < 0 || vy >= altura && vy > 0)
                    y=vy<0?2:altura-1;
                    vy*= -1;
            }
            Thread.sleep(10);
            limpiarPantalla();
        }while(!salir);
    }
}
