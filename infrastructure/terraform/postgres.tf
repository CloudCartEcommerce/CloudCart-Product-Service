resource "azurerm_postgresql_flexible_server" "product_db" {
  location            = var.location
  name                = var.postgres_server_name
  resource_group_name = var.resource_group_name
  zone                = "2"
  tags = {
    Environment = "Development"
    Project     = "CloudCart"
    Service     = "Product-Service"
  }
}

resource "azurerm_postgresql_flexible_server_database" "product_database" {
  name      = var.postgres_database_name
  server_id = azurerm_postgresql_flexible_server.product_db.id
  charset   = "UTF8"
  collation = "en_US.utf8"
}