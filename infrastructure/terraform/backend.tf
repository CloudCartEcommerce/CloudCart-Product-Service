terraform {
  backend "azurerm" {
    resource_group_name  = "cloudcart-dev-rg"
    storage_account_name = "cloudcartterraformstate"
    container_name       = "tfstate"
    key                  = "product-service.tfstate"
  }
}