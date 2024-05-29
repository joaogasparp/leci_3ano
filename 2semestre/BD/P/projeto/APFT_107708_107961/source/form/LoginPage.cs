using System;
using System.Windows.Forms;

namespace form
{
    public partial class LoginPage : Form
    {
        public LoginPage()
        {
            InitializeComponent();
        }

        private void label1_Click(object sender, EventArgs e)
        {

        }

        private void textBox1_TextChanged(object sender, EventArgs e)
        {

        }

        private void label3_Click(object sender, EventArgs e)
        {

        }

        private void passe_TextChanged(object sender, EventArgs e)
        {

        }

        private void button1_Click(object sender, EventArgs e)
        {
            if ((utilizador.Text == "area1" && passe.Text == "gerente1") || (utilizador.Text == "area2" && passe.Text == "gerente2") || (utilizador.Text == "area3" && passe.Text == "gerente3") || (utilizador.Text == "area4" && passe.Text == "gerente4"))
            {
                AreaServicoPage areaServicoPage = new AreaServicoPage();
                this.Hide();
                areaServicoPage.Show();
            }
            else
            {
                MessageBox.Show("Credenciais inválidas. Tente novamente.");
            }
        }
    }
}
