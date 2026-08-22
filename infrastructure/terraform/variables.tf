variable "resource_group_name" {
  description = "Azure Resource Group Name"
  type        = string
  default     = "cloudcart-dev-rg"
}

variable "location" {
  description = "Azure Region"
  type        = string
  default     = "Central India"
}

variable "postgres_server_name" {
  description = "Azure PostgreSQL Flexible Server Name"
  type        = string
  default     = "cloudcart-postgres-dev"
}

variable "postgres_database_name" {
  description = "PostgreSQL database name for Product Service"
  type        = string
  default     = "cloudcart_product_db"
}

variable "key_vault_name" {
  description = "Azure Key Vault name"
  type        = string
  default     = "cloudcart-kv-dev-arjun"
}