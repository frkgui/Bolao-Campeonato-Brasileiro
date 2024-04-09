public class Partida {
    private Time timeUm;
    private Time timeDois;
    private Integer placarTimeUm;
    private Integer placarTimeDois;

    public Partida(Time timeUm, Time timeDois, Integer placarTimeUm, Integer placarTimeDois) {
        this.timeUm = timeUm;
        this.timeDois = timeDois;
        this.placarTimeUm = placarTimeUm;
        this.placarTimeDois = placarTimeDois;
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
}
