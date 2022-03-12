public class Body {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    static final double gravConstant = 6.67E-11;

    public Body(double xP, double yP, double xV,
               double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Body(Body b) {

    }

    public double calcDistance(Body body) {
        double dx = Math.abs(this.xxPos - body.xxPos);
        double dy = Math.abs(this.yyPos - body.yyPos);
        double rsquared = Math.pow(dx, 2) + Math.pow(dy, 2);

        return Math.sqrt(rsquared);
    }

    public double calcForceExertedBy(Body body){
        return Math.abs((gravConstant * this.mass * body.mass) / (Math.pow(calcDistance(body), 2)));
    }

    public double calcForceExertedByX(Body body) {
        double distance = calcDistance(body);
        double force = calcForceExertedBy(body);
        return force * (body.xxPos - this.xxPos) / distance;
    }

    public double calcForceExertedByY(Body body) {
        double distance = calcDistance(body);
        double force = calcForceExertedBy(body);
        return force * (body.yyPos - this.yyPos) / distance;
    }

    public double calcNetForceExertedByX(Body [] bodies) {
        double netXForce = 0;
        double holdForce;

        for (Body body : bodies) {
            holdForce = calcForceExertedByX(body);
            if (body.equals(this)) { continue; }
            netXForce += holdForce;
        }

        return netXForce;
    }

    public double calcNetForceExertedByY(Body [] bodies) {
        double netYForce = 0;
        double holdForce;

        for (Body body : bodies) {
            holdForce = calcForceExertedByY(body);
            if (body.equals(this)) { continue; }
            netYForce += holdForce;
        }

        return netYForce;
    }

    public void update(double time, double xforce, double yforce) {
        // (posxx, posyy) --> planets coordinates
        // original xvel and yvel
        // to find acceleration for x and y

        double ax = xforce / this.mass;
        double ay = yforce / this.mass;

        // Replace old values with new (dont really need 'this' here)
        this.xxVel = xxVel + time * ax;
        this.yyVel = yyVel + time * ay;

        this.xxPos = xxPos + time * xxVel;
        this.yyPos = yyPos + time * yyVel;

    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }

}