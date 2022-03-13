public class NBody {
    public static double readRadius(String file) {
        In in = new In(file);

        int discard = in.readInt();
        double radiusOfUniverse = in.readDouble();

        return radiusOfUniverse;
    }

    public static Body[] readBodies(String file) {
        In in = new In(file);
        int numOfPlanets = in.readInt();
        double radiusOfUniverse = in.readDouble();

        Body[] bodies = new Body[numOfPlanets];
        int count = 0;

        while (count < numOfPlanets) {
            // These lines contain (x,y) of initial position, (x,y) of initial velocity, mass, and string that refers to image
            double initialPosX = in.readDouble();
            double initialPosY = in.readDouble();
            double initialVelX = in.readDouble();
            double initialVelY = in.readDouble();
            double mass = in.readDouble();
            String img = in.readString();
            Body planet = new Body(initialPosX, initialPosY, initialVelX, initialVelY, mass, img);      // creates new body to be added to array
            bodies[count] = planet;
            count++;
        }

        return bodies;
    }


    public static void main(String[] args) {
        int time = 0;
        double T = Double.valueOf(args[0]);
        double dt = Double.valueOf(args[1]);
        String fileName = args[2];

        Body[] bodies = readBodies(fileName);
        // read bodies and universe radius
        In in = new In(fileName);
        int numOfPlanets = in.readInt();
        double radiusOfUniverse = in.readDouble();

        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-radiusOfUniverse, radiusOfUniverse);

        // plays music from audio file (doesnt work)
        StdAudio.play("audio/2001.mid");

        while (time < T) {
            int count = 0;
            double[] xForces = new double[numOfPlanets];
            double[] yForces = new double[numOfPlanets];

            // Calculate net x and y foces for each body and store in xforce and yforce arrays
            for (Body b : bodies) {
                xForces[count] = b.calcNetForceExertedByX(bodies);
                yForces[count] = b.calcNetForceExertedByY(bodies);
//                b.update(dt, xForces[count], yForces[count]);   Just wanted to see what happens
                count++;
            }

            for (int i = 0; i < bodies.length; i++) {
                // for each body, call update
                bodies[i].update(dt, xForces[i], yForces[i]);
            }

            // Draw background image
            StdDraw.picture(0, 0, "images/starfield.jpg");

            // Draw bodies
            for (Body b : bodies) {
                b.draw();
            }

            // show offscreen buffer (show)
            StdDraw.show();

            // pause animation for 10 milliseconds
            StdDraw.pause(10);

            // increase time varibale by dt
            time += dt;

        }

        // After program is done, print out state of universe (WHY DOESNT IT PRINT)
        System.out.printf("%d\n", bodies.length);
        System.out.printf("%.2e\n", radiusOfUniverse);
        for (int i = 0; i < bodies.length; i++) {
            System.out.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
                    bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);
        }
    }
}
