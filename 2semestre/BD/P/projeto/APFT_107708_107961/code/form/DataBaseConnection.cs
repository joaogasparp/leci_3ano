using System;
using System.Data;
using System.Data.SqlClient;
using System.Diagnostics;

namespace form
{
    public static class DatabaseConnection
    {
        private static string dataSource = "tcp:mednat.ieeta.pt\\SQLSERVER,8101";
        private static string initialCatalog = "p1g8";
        private static string uid = "p1g8";
        private static string password = "IrineuSonic28g*";

        public static string connectionString = $"Data Source = {dataSource}; Initial Catalog = {initialCatalog}; uid= {uid}; password = {password}";

        public static SqlConnection GetConnection()
        {
            return new SqlConnection(connectionString);
        }
    }
}