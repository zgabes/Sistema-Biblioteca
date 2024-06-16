package entidades;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Multa {
    private Integer id;
    private LocalDateTime dataAplicada;
    private Boolean pago;
    private Emprestimo emprestimo;

    public Multa(Integer id, Emprestimo emprestimo) {
        this.id = id;
        this.dataAplicada = LocalDateTime.now();
        this.pago = false;
        this.emprestimo = emprestimo;
    }

    @Override
    public String toString() {
        return "Multa{" +
                "id=" + id +
                ", dataAplicada=" + dataAplicada +
                ", pago=" + pago +
                ", emprestimo=" + emprestimo.getId() +
                '}';
    }

    public Emprestimo getEmprestimo() {
        return emprestimo;
    }

    public LocalDateTime getDataAplicada() {
        return dataAplicada;
    }

    public Boolean getPago() {
        return pago;
    }

    public Integer getId() {
        return id;
    }

    public Long calculaMulta() {
        LocalDateTime now = LocalDateTime.now();
        long diasAtraso = ChronoUnit.DAYS.between(dataAplicada, now);
        return Math.max(5, diasAtraso * 5);
    }

    public void pagaMulta() {
        this.pago = true;
        this.emprestimo.marcarComoDevolvido();
    }
}
