public class NBody {

    public static double  readRadius(String dir){
        In in = new In(dir);
        int firstItemInFile = in.readInt();
        return in.readDouble();
    }

    public static Planet[] readPlanets(String dir){
        In in = new In(dir);
        int planetNum = in.readInt();
        in.readDouble();

        Planet[] planets = new Planet[planetNum];
        int i = 0;
        while (i < planetNum){
            planets[i] = new Planet(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readString());
            i++;
        }
        return planets;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double universeRradius = readRadius(filename);
        Planet[] planets =  readPlanets(filename);

        StdDraw.setScale(-universeRradius, universeRradius);
        StdDraw.clear();
        //StdDraw.picture(0, 0, "images/starfield.jpg");

        StdDraw.enableDoubleBuffering();
        for (int t = 0; t <= T; t += dt) {
            double [] xForces = new double[planets.length];
            double [] yForces = new double[planets.length];
            int i = 0;
            while (i < planets.length){
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
                i++;
            }
            i = 0;
            while (i < planets.length){
                planets[i].update(dt,xForces[i],yForces[i]);
                i++;
            }
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (Planet planet : planets){
                planet.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);

        }

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", universeRradius);
        for (Planet planet : planets) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planet.xxPos, planet.yyPos, planet.xxVel,
                    planet.yyVel, planet.mass, planet.imgFileName);
        }



    }
}
