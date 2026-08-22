terraform {
  backend "azurerm" {
    resource_group_name  = "cloudcart-dev-rg"
    storage_account_name = "cloudcartterraformstate"
    container_name       = "tfstate"
    key                  = "product-service.tfstate"
    # Enables OIDC authentication for the Azure Blob backend.
    use_oidc = true

    # Uses Microsoft Entra ID instead of storage account access keys.
    use_azuread_auth = true
  }
}