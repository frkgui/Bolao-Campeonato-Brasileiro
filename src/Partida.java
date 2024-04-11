public class Partida {
    private Integer idPartida = 0;
    private Time timeUm;
    private Time timeDois;
    private Integer placarTimeUm;
    private Integer placarTimeDois;


    public Partida(Time timeUm, Time timeDois) {
        this.timeUm = timeUm;
        this.timeDois = timeDois;
        this.idPartida +=1;

    }

    public Integer getIdPartida() {
        return idPartida;
    }

    public void setIdPartida(Integer idPartida) {
        this.idPartida = idPartida;
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
