resource "azurerm_container_registry" "product_acr" {
  location             = var.location
  name                 = var.acr_name
  resource_group_name  = var.resource_group_name
  sku                  = "Basic"
  admin_enabled        = false
  role_assignment_mode = "AbacRepositoryPermissions"
  tags = {
    Environment = "Development"
    Project     = "CloudCart"
    Service     = "Product-Service"
  }
}