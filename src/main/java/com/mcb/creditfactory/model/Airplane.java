package com.mcb.creditfactory.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

@Entity
@Getter
@Setter
@Table(name = "AIRPLANE")
public class Airplane implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "AIRPLANE_ID")
  private Long id;

  @Column(name = "BRAND")
  private String brand;

  @Column(name = "MODEL")
  private String model;

  @Column(name = "MANUFACTURER")
  private String manufacturer;

  @Column(name = "YEAR_OF_ISSUE")
  private Short year;

  @Column(name = "FUEL_CAPACITY")
  private Integer fuelCapacity;

  @Column(name = "SEATS")
  private Integer seats;

  @Positive
  @Transient
  private BigDecimal value;

  @Transient
  private  java.util.Date dateOfEstimate ;

  @OneToMany(
      targetEntity = AirplaneCostEstimates.class,
      mappedBy = "airplane",
      fetch = FetchType.LAZY,
      cascade = CascadeType.ALL)
  private List<AirplaneCostEstimates> airplaneCostEstimates = new ArrayList<>();

@Transactional
  public void addAirplaneCostEstimates(AirplaneCostEstimates estimates){
    this.airplaneCostEstimates.add(estimates);
    estimates.setAirplane(this);
  }

  public Airplane() {
  }

  @Override
  public String toString() {
    return "Airplane{" +
        "id=" + id +
        ", year=" + year +
        ", value=" + value +
        ", dateOfEstimate=" + dateOfEstimate +
        '}';
  }

  public Airplane(Long id, String brand, String model, String manufacturer, Short year,
      Integer fuelCapacity, Integer seats, BigDecimal value,java.util.Date dateOfEstimate) {
    this.id = id;
    this.brand = brand;
    this.model = model;
    this.manufacturer = manufacturer;
    this.year = year;
    this.fuelCapacity = fuelCapacity;
    this.seats = seats;
    this.value = value;
    this.dateOfEstimate = dateOfEstimate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Airplane)) {
      return false;
    }

    Airplane airplane = (Airplane) o;

    if (brand != null ? !brand.equals(airplane.brand) : airplane.brand != null) {
      return false;
    }
    if (model != null ? !model.equals(airplane.model) : airplane.model != null) {
      return false;
    }
    if (manufacturer != null ? !manufacturer.equals(airplane.manufacturer)
        : airplane.manufacturer != null) {
      return false;
    }
    if (year != null ? !year.equals(airplane.year) : airplane.year != null) {
      return false;
    }
    if (fuelCapacity != null ? !fuelCapacity.equals(airplane.fuelCapacity)
        : airplane.fuelCapacity != null) {
      return false;
    }
    if (seats != null ? !seats.equals(airplane.seats) : airplane.seats != null) {
      return false;
    }
    if (!value.equals(airplane.value)) {
      return false;
    }
    return dateOfEstimate.equals(airplane.dateOfEstimate);
  }

  @Override
  public int hashCode() {
    int result = brand != null ? brand.hashCode() : 0;
    result = 31 * result + (model != null ? model.hashCode() : 0);
    result = 31 * result + (manufacturer != null ? manufacturer.hashCode() : 0);
    result = 31 * result + (year != null ? year.hashCode() : 0);
    result = 31 * result + (fuelCapacity != null ? fuelCapacity.hashCode() : 0);
    result = 31 * result + (seats != null ? seats.hashCode() : 0);
    result = 31 * result + value.hashCode();
    result = 31 * result + dateOfEstimate.hashCode();
    return result;
  }
}
