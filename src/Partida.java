public class Partida {
    private Time timeUm;
    private Time timeDois;
    private Integer placarTimeUm;
    private Integer placarTimeDois;

    public Partida(){

    }

    public Partida(Time timeUm, Time timeDois) {
        this.timeUm = timeUm;
        this.timeDois = timeDois;
        this.placarTimeUm = 0;
        this.placarTimeDois = 0;

    }


    public Time getTimeUm() {
        return timeUm;
    }

    public void setTimeUm(Time timeUm) {
        this.timeUm = timeUm;
    }

    public Time getTimeDois() {
        return timeDois;
    }

    public void setTimeDois(Time timeDois) {
        this.timeDois = timeDois;
    }

    public Integer getPlacarTimeUm() {
        return placarTimeUm;
    }

    public void setPlacarTimeUm(Integer placarTimeUm) {
        this.placarTimeUm = placarTimeUm;
    }

    public Integer getPlacarTimeDois() {
        return placarTimeDois;
    }

    public void setPlacarTimeDois(Integer placarTimeDois) {
        this.placarTimeDois = placarTimeDois;
    }

    @Override
    public String toString() {
        return "Partida{" +
                ", timeUm=" + timeUm +
                ", timeDois=" + timeDois +
                ", placarTimeUm=" + placarTimeUm +
                ", placarTimeDois=" + placarTimeDois +
                '}';
    }
}
