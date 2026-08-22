resource "azurerm_key_vault" "product_kv" {

  name                = var.key_vault_name
  location            = var.location
  resource_group_name = var.resource_group_name

  tenant_id = data.azurerm_client_config.current.tenant_id

  sku_name = "standard"

  rbac_authorization_enabled = true

  soft_delete_retention_days = 90

  purge_protection_enabled = true

  tags = {
    Environment = "Development"
    Project     = "CloudCart"
    Service     = "Product-Service"
  }
}

data "azurerm_client_config" "current" {}