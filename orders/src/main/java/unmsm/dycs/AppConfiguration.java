package unmsm.dycs;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

public class AppConfiguration extends Configuration {
  @NotBlank
  @NotNull
  private String securityServiceBaseUrl;
  @Valid
  @NotNull
  private DataSourceFactory database = new DataSourceFactory();

  @JsonProperty("database")
  public void setDataSourceFactory(DataSourceFactory factory) {
    this.database = factory;
  }

  @JsonProperty("database")
  public DataSourceFactory getDataSourceFactory() {
    return database;
  }

  @NotNull
  public String getSecurityServiceBaseUrl() {
    return securityServiceBaseUrl;
  }

  public void setSecurityServiceBaseUrl(@NotNull String securityServiceBaseUrl) {
    this.securityServiceBaseUrl = securityServiceBaseUrl;
  }

}

