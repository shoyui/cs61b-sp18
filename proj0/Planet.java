public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xP, double yP, double xV, double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }
    public Planet(Planet p){
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p){
        return Math.sqrt((xxPos - p.xxPos) * (xxPos - p.xxPos) + (yyPos - p.yyPos) * (yyPos - p.yyPos));
    }

    public double calcForceExertedBy(Planet p){
        double distance = calcDistance(p);

        return 6.67e-11 * p.mass * mass / (distance * distance);
    }

    public double calcForceExertedByX(Planet p){
        double force = calcForceExertedBy(p);
        double distance = calcDistance(p);
        return force * (p.xxPos - xxPos) / distance;

    }

    public double calcForceExertedByY(Planet p){
        double force = calcForceExertedBy(p);
        double distance = calcDistance(p);
        return force * (p.yyPos - yyPos) / distance;
    }

    public double calcNetForceExertedByX(Planet[] allPlanets){
        int numPlanets = allPlanets.length;
        double netForce = 0;
        for (Planet planetA : allPlanets){
            if (!this.equals(planetA)){
                netForce = netForce + calcForceExertedByX(planetA);
            }

        }
        return netForce;
    }

    public double calcNetForceExertedByY(Planet[] allPlanets){
        int numPlanets = allPlanets.length;
        double netForce = 0;
        for (Planet planetA : allPlanets){
            if (!this.equals(planetA)){
                netForce = netForce + calcForceExertedByY(planetA);
            }
        }
        return netForce;
    }

    public void update(double time, double xForce, double yForce){
        double aNetX = xForce / mass;
        double aNetY = yForce / mass;
        xxVel = xxVel + time * aNetX;
        yyVel = yyVel + time * aNetY;
        xxPos = xxPos + time * xxVel;
        yyPos = yyPos + time * yyVel;

    }

    public void draw(){
        String imgPath = "./images/" + imgFileName;
        StdDraw.picture(xxPos, yyPos, imgPath);
    }

}
