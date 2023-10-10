# tapr-2023-equipe1-curso-java

## Autenticação no AZURE
[DOC](https://learn.microsoft.com/en-us/cli/azure/install-azure-cli-linux?pivots=apt)

```
az login -u eduardo.anzardo@univille.br
az ad signed-in-user show

az cosmosdb sql role assignment create --account-name cosmoseduardoanzardo --resource-group rg-tapr2023-brazilsouth-dev --role-assignment-id 00000000-0000-0000-0000-000000000002 --role-definition-name "Cosmos DB Built-in Data Contributor" --scope "/" --principal-id 870282f0-bcd3-48b1-9293-a5c5488f7b10
```