/*

.______   .______          ___   ____    ____  _______ .______     ______   .___________.    _______.
|   _  \  |   _  \        /   \  \   \  /   / |   ____||   _  \   /  __  \  |           |   /       |
|  |_)  | |  |_)  |      /  ^  \  \   \/   /  |  |__   |  |_)  | |  |  |  | `---|  |----`  |   (----`
|   _  <  |      /      /  /_\  \  \      /   |   __|  |   _  <  |  |  |  |     |  |        \   \    
|  |_)  | |  |\  \----./  _____  \  \    /    |  |____ |  |_)  | |  `--'  |     |  |    .----)   |   
|______/  | _| `._____/__/     \__\  \__/     |_______||______/   \______/      |__|    |_______/    
                                                                                                     
*/

package frc.robot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.GenericHID.*;


public class Carousel
{
    private Pneumatics shooter;
    private Position m_carousel;
    private int k_PositionLoaded;
    private int k_BallsLoaded;
    private int k_defaultPosition;

    public Carousel(Pneumatics s, Position c)
    {

        shooter = s;
        m_carousel = c;
        m_carousel.initializePositionalPID();

    }

    public void spinCarousel()
    {
        //Case 1: The Position is Loaded with a Ball and need to load to the shooter
        //Case 2: The Position is not loaded with a ball and needs to shift to the next slot
        switch(k_PositionLoaded)
        {
            case 1:
            shooter.activate();
            break;
            
            case 2:
            do
            {
            m_carousel.runController(0.01);
            }
            while(k_PositionLoaded == 2 && m_carousel.getEncoderVal() > k_defaultPosition);
            k_PositionLoaded = 1;
            break;

            
        }
    }

    public int getBallsLoaded()
    {
        return k_BallsLoaded;
    }

    

    


}